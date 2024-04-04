import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';
import { Metadata } from '../interfaces/Metadata';

const SERVER_URL = 'http://localhost:8080/api/v1';

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  getMetadataFiles(typeURL: string): Observable<any> {
    
    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.storageService.getUser()?.accessToken);
    
    return this.http.get(SERVER_URL + typeURL, {headers});
  }
}
