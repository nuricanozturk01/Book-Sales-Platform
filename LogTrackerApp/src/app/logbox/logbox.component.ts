import {Component, OnInit} from '@angular/core';
import {WebSocketService} from "../web-socket.service";

@Component({
  selector: 'app-logbox',
  templateUrl: './logbox.component.html',
  styleUrls: ['./logbox.component.css']
})
export class LogboxComponent implements OnInit {
  messages: string[] = [];


  constructor(private webSocketService: WebSocketService) {
  }

  ngOnInit(): void {
    this.webSocketService.connect();
    this.webSocketService.onMessage().subscribe(message => {
      this.messages.push(message);
    });
  }
}
