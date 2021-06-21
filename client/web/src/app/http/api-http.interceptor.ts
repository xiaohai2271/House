import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent, HttpInterceptor,
} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";

@Injectable()
export class ApiHttpInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log(environment.host + request.url);
    const req = request.clone({
      url: environment.host + request.url,
      withCredentials: true
    })
    return next.handle(req);
  }
}
