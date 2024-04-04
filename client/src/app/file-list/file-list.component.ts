import { AfterViewInit, Component, Input, OnChanges, OnInit } from '@angular/core';
import { Metadata } from '../interfaces/Metadata';
import { MetadataService } from '../services/metadata.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { DatePipe } from '@angular/common';
import { MatCard, MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { FileListItemComponent } from "../file-list-item/file-list-item.component";

@Component({
    selector: 'app-file-list',
    standalone: true,
    templateUrl: './file-list.component.html',
    styleUrl: './file-list.component.scss',
    imports: [MatListModule, MatIconModule, MatDividerModule, DatePipe, MatCardModule, MatButtonModule, FileListItemComponent]
})
export class FileListComponent implements OnInit {

  constructor(private metaDataService: MetadataService, private router: Router){

  }
  
  metadata: Metadata[] = [];


  ngOnInit(): void {
    this.getMetadata(this.router.url); 
  }

  getMetadata(typeURL: string) {
    this.metaDataService.getMetadataFiles(typeURL).subscribe({
      next: (data: Metadata[]) => {
        this.metadata = data;
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      }
    })
  }

}
