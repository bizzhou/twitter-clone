import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {HttpClient, HttpParams} from '@angular/common/http';
import {FormGroup} from '@angular/forms';
import {Post} from './post.model';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  email: string;
  password: string;
  message: string;
  posts: Post[];
  loginStatus: boolean;

  constructor(public dialog: MatDialog, public http: HttpClient) {
  }

  openLoginDialog(): void {

    if (this.loginStatus) {
      localStorage.clear();
      window.location.reload();
    } else {
      let dialogRef = this.dialog.open(LoginDialog, {
        width: '250px',
        data: {email: this.email, password: this.password}
      });

      dialogRef.afterClosed().subscribe(result => {
        this.email = null;
        this.password = null;
      });
    }

  }

  tweet(message: string) {

    if (message.length === 0) {
      return;
    }

    const userId = localStorage.getItem('userId');

    const param = new HttpParams().set('post', message).set('id', userId);
    this.http.post('http://localhost:8080/api/tweet', param).subscribe(result => {
      this.message = '';

      this.getAllTweets(userId);
    });
  }

  ngOnInit(): void {

    const userId = localStorage.getItem('userId');
    this.loginStatus = false;

    if (userId !== undefined && userId !== null) {
      this.getAllTweets(userId);
      this.loginStatus = true;
    }

  }

  isLoggedIn() {
    return this.loginStatus;
  }


  private getAllTweets(userId) {
    this.http.get(`http://localhost:8080/api/get_tweets/${userId}`).subscribe(result => {
      this.posts = result as Post[];
      this.posts = this.posts.reverse();
    });
  }
}


@Component({
  selector: 'app-login-modal',
  templateUrl: 'login.html',
})
export class LoginDialog {

  constructor(
    public dialogRef: MatDialogRef<LoginDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any, public http: HttpClient) {
  }

  logIn(email: string, password: string): void {

    const param = new HttpParams().set('email', email).set('password', password);

    this.http.post('http://localhost:8080/api/login', param).subscribe(result => {
      localStorage.setItem('userId', result['userId']);
      this.dialogRef.close();
      window.location.reload();
    }, error1 => {
      console.log('', error1);
      alert(error1['error']['message']);
    });
  }

}

