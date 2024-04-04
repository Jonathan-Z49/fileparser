import { Metadata } from "./Metadata";

export interface ParsedData{
    id: string;
    data: Object;
    metadata: Metadata;
    user: string;
}