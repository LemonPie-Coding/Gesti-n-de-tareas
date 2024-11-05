package com.felipe.principal.services;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BaseService {
        //buscar todos los registros
        public abstract Object findAll();

        //buscar un registro por id
        public abstract Object findById(Long id);
    
        //crear y actualizar un registro
        public abstract Object create(Object object);

        //eliminar un registro
        public abstract void deleteById(Long id);

}
