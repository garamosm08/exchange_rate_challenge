import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

//const API_URL = 'http://20.241.213.146:8080/api/';
const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getAllCurrencies(): Observable<any> {
    return this.http.get(API_URL + 'currencies', { responseType: 'json' });
  }

  getAllExchangeTypes(): Observable<any> {
    return this.http.get(API_URL + 'exchangetypes', { responseType: 'json' });
  }

  getUserAccounts(): Observable<any> {
    return this.http.get(API_URL + 'accounts', { responseType: 'json' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  doExchangeRate(request: object): Observable<any> {
    return this.http.post(API_URL + 'exchangerates', request, { observe: 'response' });
  }
}
