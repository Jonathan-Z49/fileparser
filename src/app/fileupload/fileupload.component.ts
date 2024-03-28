import { Component, OnChanges } from '@angular/core';
import { FileuploadService } from '../services/fileupload.service';
import { Observable } from 'rxjs';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { MatList, MatListItem } from '@angular/material/list';
import { MatButton } from '@angular/material/button';
import { MatDivider } from '@angular/material/divider';
import { ParsedData } from '../interfaces/ParsedDataRes';
import { RecordsComponent } from '../records/records.component';

@Component({
  selector: 'app-fileupload',
  standalone: true,
  imports: [MatList, MatDivider, MatListItem, MatButton, RecordsComponent],
  templateUrl: './fileupload.component.html',
  styleUrl: './fileupload.component.scss'
})
export class FileuploadComponent {

  constructor(private fileUploadService: FileuploadService) {}

  specFile?: File;
  flatFile?: File;

  allRecords = {arr: []};

  selectSpecFile(event: any): void { 
    this.specFile = event.target.files[0];
  }

  selectFlatFile(event: any): void {
    this.flatFile = event.target.files[0];
  }

  upload(): void {
    this.fileUploadService.upload(this.specFile!, this.flatFile!).subscribe({
      next: (data: any) => {
        console.log(data);
        this.allRecords = {arr: JSON.parse(data)};
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      }
    });
  }

}
