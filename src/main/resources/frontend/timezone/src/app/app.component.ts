import {Component, OnDestroy, OnInit} from '@angular/core';
import {TimezoneService} from "./services/timezone.service";
import * as moment from 'moment';
import {Moment} from 'moment';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  time: Moment;
  timezones: string[];
  private subscriptions: Subscription[] = [];
  private morningStart = moment("6:00", "H:mm");
  private dayStart = moment("12:00", "H:mm");
  private nightStart = moment("21:00", "H:mm");

  constructor(private service: TimezoneService) {
  }

  get morning(): boolean {
    if (this.time !== undefined) {
      return this.time.isAfter(this.morningStart) && this.time.isBefore(this.dayStart);
    }
  }

  get day(): boolean {
    if (this.time !== undefined) {
      return this.time.isAfter(this.dayStart) && this.time.isBefore(this.nightStart)
    }
  }

  get night(): boolean {
    if (this.time !== undefined) {
      return !this.day && !this.morning;
    }
  }

  ngOnInit(): void {
    // load default time
    this.loadDefaultTime();
    // load timezones
    this.loadTimeZones();
  }

  selectTimezone(newTimezone: string): void {
    this.subscriptions.push(
      this.service.getTime(newTimezone).subscribe((time: string) => {
        this.time = moment(time, "H:mm");
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((sub) => sub.unsubscribe());
  }

  private loadDefaultTime(): void {
    this.subscriptions.push(
      this.service.getTime().subscribe((time: string) => {
        this.time = moment(time, "H:mm");
      })
    );
  }

  private loadTimeZones(): void {
    this.subscriptions.push(
      this.service.getTimezone().subscribe((zones: string[]) => {
        this.timezones = zones;
      })
    );
  }

}
