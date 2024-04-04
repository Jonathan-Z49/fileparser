import {
  AfterViewInit,
  Component,
  Input,
  OnChanges,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule, Sort } from '@angular/material/sort';
import { ParsedData } from '../interfaces/ParsedDataRes';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { NgClass } from '@angular/common';
import { RecordService } from '../services/record.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-records',
  standalone: true,
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    NgClass,
  ],
  templateUrl: './records.component.html',
  styleUrl: './records.component.scss',
})
export class RecordsComponent implements OnChanges, AfterViewInit, OnInit {
  constructor(
    private activatedRoute: ActivatedRoute,
    private recordService: RecordService
  ) {}

  @Input() records: ParsedData[] = [];
  displayedColumns: string[] = [];
  dataSource = new MatTableDataSource<Object>([]);
  recordId: string | null = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  @ViewChild(MatSort) sort!: MatSort;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit() {
    this.recordId = this.activatedRoute.snapshot.paramMap.get('id');
    this.activatedRoute.paramMap.subscribe((params: ParamMap) => {
      this.recordId = params.get('id');
      if (this.recordId) {
        this.getRecordHistory(this.recordId);
      }
    });
  }

  ngAfterViewInit() {
      if(this.records.length){
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
  }

  ngOnChanges() {
      if(this.records.length) {
        this.setup();
      }
  }

  parseFields() {
    if (this.records.length) {
      this.displayedColumns = Object.keys(this.records[0].data);
    }
  }

  setup() {
    this.parseFields();
    this.dataSource = new MatTableDataSource(this.flatten());
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getRecordHistory(fileId: string) {
    this.recordService.getRecordsByFile(fileId).subscribe({
      next: (data: ParsedData[]) => {
        this.records = data;
        console.log(data);
        
        this.setup();
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
      },
    });
  }

  flatten(): Object[] {
    if (this.records.length) {
      return this.records.map((record) => {
        const { data } = record;
        return data;
      });
    }
    return [];
  }
}
