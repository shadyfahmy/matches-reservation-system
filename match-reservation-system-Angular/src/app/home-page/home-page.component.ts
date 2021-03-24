import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  msg: any;
  stompClient = null;

  constructor() { }

  ngOnInit() {
    this.connect();
    //this.sendName();
  }

  connect() {
    var socket = new SockJS('http://localhost:8080/ws');
    this.stompClient = Stomp.over(socket);
    console.log(this.stompClient)
    this.stompClient.connect();/*{}, function (frame) {
        //setConnected(true);
        console.log(this.stompClient)
        console.log('Connected: ' + frame);
        this.stompClient.subscribe('/topic/greetings', function (greeting) {
            this.msg = JSON.parse(greeting.body).content;
        });
    });*/
  }
  sendName() {
    this.stompClient.send("/app/hello", {}, JSON.stringify({'content': 'refreeesh'}));
  }

  sub() {
    this.stompClient.subscribe('/topic/greetings', function (greeting) {
      console.log("recieveeeed")
    });
  }
  

}
