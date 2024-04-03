import { DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCard } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { Metadata } from '../interfaces/Metadata';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-file-list-item',
  standalone: true,
  imports: [MatListModule, MatIconModule, MatDividerModule, DatePipe, MatCard, MatButtonModule],
  templateUrl: './file-list-item.component.html',
  styleUrl: './file-list-item.component.scss'
})
export class FileListItemComponent {
    @Input() fileMetadata!: Metadata;

    constructor(private router: Router) {

    }

    navigateToRecordPage() {
      this.router.navigate([`files/${this.fileMetadata.id}/records`])
    }

}
