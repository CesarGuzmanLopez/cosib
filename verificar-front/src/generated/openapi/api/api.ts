export * from './documentos.service';
import { DocumentosService } from './documentos.service';
export * from './usuarios.service';
import { UsuariosService } from './usuarios.service';
export const APIS = [DocumentosService, UsuariosService];
