export interface JWTRes {
    accessToken: string;
    id: string;
    roles: string[];
    tokenType: string;
    username: string;
}