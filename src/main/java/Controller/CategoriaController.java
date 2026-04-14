package Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.Categoria;
import service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {


   private final CategoriaService categoriaService;


   public CategoriaController(CategoriaService categoriaService) {
       this.categoriaService = categoriaService;
   }


   @GetMapping
   public ResponseEntity<List<Categoria>> listarTodos() {
       List<Categoria> categorias = categoriaService.listarTodos();
       return ResponseEntity.ok(categorias);
   }


   @GetMapping("/{id}")
   public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
       return categoriaService.obtenerPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }


   @PostMapping
   public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
       Categoria nuevaCategoria = categoriaService.guardar(categoria);
       return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
   }


   @PutMapping("/{id}")
   public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
       try {
           Categoria categoriaActualizada = categoriaService.actualizar(id, categoria);
           return ResponseEntity.ok(categoriaActualizada);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
   }


   @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminar(@PathVariable Long id) {
       categoriaService.eliminar(id);
       return ResponseEntity.noContent().build();
   }
}

