import { HttpClient, HttpEvent, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

const SERVER_URL = 'http://localhost:8080/api/v1/files';

@Injectable({
  providedIn: 'root'
})
export class FileuploadService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  upload(specFile: File, dataFile: File): Observable<any> {
    const formData: FormData = new FormData();

    formData.append('specFile', specFile);
    formData.append('dataFile', dataFile);

    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.storageService.getUser()?.accessToken);
    
    return this.http.post(SERVER_URL, formData, {headers});
  }

}
