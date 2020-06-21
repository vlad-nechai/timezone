import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TimezoneService {

  private apiUrl = environment.server;

  constructor(private http: HttpClient) { }

  getTime(timezone?: string) {

    let params = new HttpParams();
    if (timezone !== null && timezone !== undefined) {
      params = new HttpParams()
        .set('zoneId', timezone);
    }

    return this.http.get(`${(this.apiUrl)}/time`,
      {
        params: params,
        responseType: 'text'
      });
  }

  getTimezone() {
    return this.http.get(`${(this.apiUrl)}/zones`);
  }
}
