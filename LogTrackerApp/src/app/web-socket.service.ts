import {Injectable} from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'webstomp-client';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private stompClient: any;
  private messageSubject = new Subject<string>();

  constructor() {
  }

  public connect(): void {
    const socket = new SockJS('http://localhost:8089/socket-endpoint');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, frame => {
      this.stompClient.subscribe('/log/receivedMessage', message => {
        this.messageSubject.next(message.body);
      });
    });
  }

  public onMessage(): Observable<string> {
    return this.messageSubject.asObservable();
  }
}
