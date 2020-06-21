import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TimezoneService {

  private apiUrl = environment.server;

  constructor(private http: HttpClient) { }

  getTime() {
    return this.http.get(`${(this.apiUrl)}/time`);
  }

  getTimezone() {
    return this.http.get(`${(this.apiUrl)}/zones`);
  }
}
