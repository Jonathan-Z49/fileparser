import { Injectable } from '@angular/core';
import { JWTRes } from '../interfaces/JWTres';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  clear() {
    window.sessionStorage.clear();
  }

  setUser(user: JWTRes) {
    this.clear();
    window.sessionStorage.setItem("user", JSON.stringify(user));
  }

  getUser(): JWTRes | null {
    const user = window.sessionStorage.getItem("user");

    if(user) {
      return JSON.parse(user);
    }
    return null;
  }

}
