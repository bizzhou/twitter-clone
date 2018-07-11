import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  /**
   * Handles when user click on the person button
   */
  openLoginDialog(): void {

    if (this.loginStatus) {
      localStorage.clear();
      window.location.reload();
    } else {
      const dialogRef = this.dialog.open(LoginDialog, {
        width: '250px',
        data: {email: this.email, password: this.password}
      });

      dialogRef.afterClosed().subscribe(result => {
        this.email = null;
        this.password = null;
      });
    }

  }

  /**
   * Tweet a message
   *
   * @param {string} message
   */
  tweet(message: string): void {

    if (message.length === 0) {
      return;
    }

    const userId = localStorage.getItem('userId');

    // Build the request parameter
    const param = new HttpParams().set('post', message).set('id', userId);

    // Make a request to backend
    this.http.post('http://localhost:8080/api/tweet', param).subscribe(result => {
      this.message = '';
      this.getAllTweets(userId);
    });
  }

  /**
   * Init method for the website
   */
  ngOnInit(): void {

    const userId = localStorage.getItem('userId');
    this.loginStatus = false;

    if (userId !== undefined && userId !== null) {
      this.getAllTweets(userId);
      this.loginStatus = true;
    }

  }

  /**
   * Check if user is login
   *
   * @returns {boolean}
   */
  isLoggedIn(): boolean {
    return this.loginStatus;
  }


  /**
   * Get all posts for user
   *
   * @param userId
   */
  private getAllTweets(userId): void {
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

  /**
   * Logs user in
   *
   * @param {string} email
   * @param {string} password
   */
  logIn(email: string, password: string): void {

    // Build the request parameter
    const param = new HttpParams().set('email', email).set('password', password);

    // Send request to check login
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

