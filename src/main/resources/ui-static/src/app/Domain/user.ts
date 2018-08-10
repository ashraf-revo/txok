export class User {
  id: string;
  type: string;
  email: string;
  enabled: boolean;
  username: string;
  password: string;
  accountNonExpired: boolean;
  accountNonLocked: boolean;
  credentialsNonExpired: boolean;
  authorities: Array<any>;
}
