// express.d.ts
declare namespace Express {
  export interface Request {
    user?: any; // Replace 'any' with the actual type of your user object
  }
}
