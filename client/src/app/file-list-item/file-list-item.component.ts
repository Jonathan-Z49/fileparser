import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCard } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { Metadata } from '../interfaces/Metadata';
import { Route, Router } from '@angular/router';
import { FileDownloadService } from '../services/file-download.service';

@Component({
  selector: 'app-file-list-item',
  standalone: true,
  imports: [MatListModule, MatIconModule, MatDividerModule, DatePipe, MatCard, MatButtonModule],
  templateUrl: './file-list-item.component.html',
  styleUrl: './file-list-item.component.scss'
})
export class FileListItemComponent {
    @Input() fileMetadata!: Metadata;

    constructor(private router: Router, private fileDownloadService: FileDownloadService) {

    }

    downloadFile(){
      this.fileDownloadService.downloadFile(this.fileMetadata.fileName, this.fileMetadata.id).subscribe(res => {
        this.saveFile(res.body!)
      })
    }

    saveFile(blob: Blob) {
      const downloadLink = document.createElement('a');
      const url = window.URL.createObjectURL(blob);
      downloadLink.href = url;
      downloadLink.download = this.fileMetadata.fileName;
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
      window.URL.revokeObjectURL(url);
    }

    navigateToRecordPage() {
      this.router.navigate([`files/${this.fileMetadata.id}/records`])
    }

}
