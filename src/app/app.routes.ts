import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FileuploadComponent } from './fileupload/fileupload.component';
import { FileListComponent } from './file-list/file-list.component';
import { RecordsComponent } from './records/records.component';

export const routes: Routes = [
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: LoginComponent},
    {path: 'files', component: FileuploadComponent},
    {path: 'files/flat', component: FileListComponent},
    {path: 'files/spec', component: FileListComponent},
    {path: 'files/:id/records', component: RecordsComponent},
];
