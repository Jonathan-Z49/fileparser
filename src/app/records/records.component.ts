import { Component, Input, OnChanges } from '@angular/core';
import { MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderCellDef, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable } from '@angular/material/table';

@Component({
  selector: 'app-records',
  standalone: true,
  imports: 
  [ MatTable, MatColumnDef, MatHeaderCell, 
    MatCellDef, MatCell, MatHeaderCellDef, MatHeaderRowDef, 
    MatRow, MatHeaderRow, MatRowDef
  ],
  templateUrl: './records.component.html',
  styleUrl: './records.component.scss'
})
export class RecordsComponent implements OnChanges {
  @Input() records: any;
  displayedColumns: string[] = [];
  dataSource = [];

  ngOnChanges(){
    this.parseFields();
    this.dataSource = this.records;
  }

  parseFields() {
    if(this.records.length){
      this.displayedColumns = Object.keys(this.records[0].data);
    }
    console.log(this.displayedColumns);
  }
}
