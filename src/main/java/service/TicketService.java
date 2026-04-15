package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import model.Ticket;
import repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> obtenerPorId(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket guardar(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket actualizar(Long id, Ticket ticketActualizado) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setTitulo(ticketActualizado.getTitulo());
                    ticket.setDescripcion(ticketActualizado.getDescripcion());
                    ticket.setEstado(ticketActualizado.getEstado());
                    ticket.setPrioridad(ticketActualizado.getPrioridad());
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        ticketRepository.deleteById(id);
    }

}
