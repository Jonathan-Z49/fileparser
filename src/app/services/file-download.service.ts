import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class FileDownloadService {

  constructor(private http: HttpClient, private storageService: StorageService) { }

  downloadFile(name: string, id: string): Observable<HttpResponse<Blob>> {

    const headers = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.storageService.getUser()?.accessToken);

    return this.http.get(`http://localhost:8080/api/v1/files/${name}/${id}` , {
      headers,
      observe: 'response',
      responseType: 'blob'
    });
  }
  
}