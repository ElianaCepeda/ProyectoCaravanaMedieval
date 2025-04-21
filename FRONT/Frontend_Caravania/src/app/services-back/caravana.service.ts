import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Caravana } from '../Models/caravana';

@Injectable({
  providedIn: 'root'
})
export class CaravanaService {

  constructor(
    private http: HttpClient
  ) { }


  crearCaravanna(caravana : Caravana){
    return this.http.post('http://localhost:8081/caravana/create', caravana);
  }

  obtenerCaravanas(){
    return this.http.get('http://localhost:8081/caravana/list');
  }

  obtenerCaravana(id: number){
    return this.http.get('http://localhost:8081/caravana/' + id);
  }

  eliminarCaravana(id: number){
    return this.http.delete('http://localhost:8081/caravana/delete/' + id);
  }

  
}
