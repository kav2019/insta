import {Component, OnInit} from '@angular/core';
import {EditUserComponent} from "../edit-user/edit-user.component";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {User} from "../../models/User";
import {PostService} from "../../service/post.service";
import {NotificationService} from "../../service/notification.service";
import {ImageUploadService} from "../../service/image-upload.service";
import {TokenStorgeService} from "../../service/token-storge.service";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  isUserDataLoaded = false;
  // @ts-ignore
  user: User;
  // @ts-ignore
  selectedFile: File;
  // @ts-ignore
  userProfileImage: File;
  previewImgURL: any;

  constructor(private tokenService: TokenStorgeService,
              private postService: PostService,
              private dialog: MatDialog,
              private notificationService: NotificationService,
              private imageService: ImageUploadService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getCurrentUser()
      // @ts-ignore
      .subscribe(data => {
        this.user = data;
        this.isUserDataLoaded = true;
      });

    this.imageService.getProfileImage()
      // @ts-ignore
      .subscribe(data => {
        this.userProfileImage = data.imageBytes;
      });
  }
// @ts-ignore
  onFileSelected(event): void {
    this.selectedFile = event.target.files[0];

    const reader = new FileReader();
    reader.readAsDataURL(this.selectedFile);
    reader.onload = () => {
      this.previewImgURL = reader.result;
    };
  }

  openEditDialog(): void {
    const dialogUserEditConfig = new MatDialogConfig();
    dialogUserEditConfig.width = '400px';
    dialogUserEditConfig.data = {
      user: this.user
    };
    this.dialog.open(EditUserComponent, dialogUserEditConfig);
  }

  formatImage(img: any): any {
    if (img == null) {
      return null;
    }
    return 'data:image/jpeg;base64,' + img;
  }

  onUpload(): void {
    if (this.selectedFile != null) {
      this.imageService.uploadImageToUser(this.selectedFile)
        .subscribe(() => {
          this.notificationService.showSnackBar('Profile Image updated successfully');
        });
    }
  }
}

