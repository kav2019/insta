import { Injectable } from '@angular/core';
import {NotificationService} from "../service/notification.service";
import {TokenStorgeService} from "../service/token-storge.service";
import {catchError, Observable, throwError} from "rxjs";
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor{

  constructor(private tokenService: TokenStorgeService,
              private notificationService: NotificationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(err => {
      if (err.status === 401) {
        this.tokenService.logOut();
        window.location.reload();
      }


      const error = err.error.message || err.statusText;
      this.notificationService.showSnackBar(error);
      return throwError(error);
    }));
  }
}

export const authErrorInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true}
];
