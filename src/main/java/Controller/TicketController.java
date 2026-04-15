package Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.Ticket;
import service.TicketService;



@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> listarTodos() {
        List<Ticket> tickets = ticketService.listarTodos();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerPorId(@PathVariable Long id) {
        return ticketService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ticket> crear(@RequestBody Ticket ticket) {
        Ticket nuevoTicket = ticketService.guardar(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTicket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(@PathVariable Long id, @RequestBody Ticket ticket) {
        try {
            Ticket ticketActualizado = ticketService.actualizar(id, ticket);
            return ResponseEntity.ok(ticketActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ticketService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
