import { AfterViewInit, Component, Input, OnChanges, OnInit } from '@angular/core';
import { Metadata } from '../interfaces/Metadata';
import { FlatRecordService } from '../services/flat-record.service';
import { HttpErrorResponse } from '@angular/common/http';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { DatePipe } from '@angular/common';
import { MatCard } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-file-list',
  standalone: true,
  imports: [MatListModule, MatIconModule, MatDividerModule, DatePipe, MatCard, MatButtonModule],
  templateUrl: './file-list.component.html',
  styleUrl: './file-list.component.scss'
})
export class FileListComponent implements OnInit {

  constructor(private flatRecordService: FlatRecordService){

  }
  
  metadata: Metadata[] = [];


  ngOnInit(): void {
    this.getMetadata(); 
    console.log(this.metadata);
  }

  getMetadata() {
    this.flatRecordService.getSpecFiles().subscribe({
      next: (data: Metadata[]) => {
        this.metadata = data;
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      }
    })
  }

}
