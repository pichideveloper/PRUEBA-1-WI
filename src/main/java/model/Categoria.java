package model;

import jakarta.persistence.*;
import lombok.* ;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column(nullable = false, unique = true)
   private String nombre;

   @Column
   private String descripcion;
}
