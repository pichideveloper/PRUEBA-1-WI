package service;

import org.springframework.stereotype.Service;

import model.Categoria;
import repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {


   private final CategoriaRepository categoriaRepository;


   public CategoriaService(CategoriaRepository categoriaRepository) {
       this.categoriaRepository = categoriaRepository;
   }


   public List<Categoria> listarTodos() {
       return categoriaRepository.findAll();
   }


   public Optional<Categoria> obtenerPorId(Long id) {
       return categoriaRepository.findById(id);
   }


   public Categoria guardar(Categoria categoria) {
       return categoriaRepository.save(categoria);
   }


   public Categoria actualizar(Long id, Categoria categoriaActualizada) {
       return categoriaRepository.findById(id)
               .map(categoria -> {
                   categoria.setNombre(categoriaActualizada.getNombre());
                   return categoriaRepository.save(categoria);
               })
               .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
   }


   public void eliminar(Long id) {
       categoriaRepository.deleteById(id);
   }
   public boolean existePorId(Long id) {
    return categoriaRepository.existsById(id);
   }
}
