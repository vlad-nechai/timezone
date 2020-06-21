import {Component, OnInit} from '@angular/core';
import {TimezoneService} from "./services/timezone.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title: string;
  timezones: string[];

  constructor(private service: TimezoneService) {}

  ngOnInit(): void {
    // load default time
    this.service.getTime().subscribe((time: string) => {
      this.title = time;
    });

    // load timezones
    this.service.getTimezone().subscribe((zones:string[]) => {
      this.timezones = zones;
    });
  }

  selectTimezone(newTimezone: string): void {
    this.service.getTime(newTimezone).subscribe((time:string) => {
      console.log(time);
      this.title = time;
    })
  }
}
