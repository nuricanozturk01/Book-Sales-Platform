import {Injectable, OnInit} from '@angular/core';
import {webSocket} from "rxjs/webSocket";

@Injectable({
  providedIn: 'root'
})
export class LogServiceService implements OnInit {

  webSocketSubject = webSocket('ws://localhost:8089/socket-endpoint/log/receivedMessage');

  constructor() {
  }

  ngOnInit() {
    // Listen message from websocket
    this.webSocketSubject.subscribe(
      (message) => {

      },
      (error) => {
        console.error('Error: ', error);
      }
    );
  }
}
