import {Component, OnInit} from '@angular/core';
import {TimezoneService} from "./services/timezone.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'timezone';

  constructor(private service: TimezoneService) {}

  ngOnInit(): void {
    this.service.getTime().subscribe((time: string) => {
      this.title = time;
    })
  }
}
