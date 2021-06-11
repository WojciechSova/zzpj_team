import { AccessLevel } from './AccessLevel';

export interface Account {
    id: string,
    login: string,
    firstName: string,
    lastName: string,
    password?: string,
    accountState?: number,
    accountNumber?: string,
    debt?: number,
    currency?: string,
    accessLevel: AccessLevel,
    active: boolean,
}
