using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Transito.Models;

namespace Transito.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EvidenciasController : ControllerBase
    {
        private readonly TransitoContext _context;

        public EvidenciasController(TransitoContext context)
        {
            _context = context;
        }

        // GET: api/Evidencias
        [HttpGet]
        public IEnumerable<Evidencia> GetEvidencia()
        {
            return _context.Evidencia;
        }

        // GET: api/Evidencias/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetEvidencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var evidencia = await _context.Evidencia.FindAsync(id);

            if (evidencia == null)
            {
                return NotFound();
            }

            return Ok(evidencia);
        }

        // PUT: api/Evidencias/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutEvidencia([FromRoute] int id, [FromBody] Evidencia evidencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != evidencia.Idevidencia)
            {
                return BadRequest();
            }

            _context.Entry(evidencia).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!EvidenciaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Evidencias
        [HttpPost]
        public async Task<IActionResult> PostEvidencia([FromBody] Evidencia evidencia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Evidencia.Add(evidencia);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetEvidencia", new { id = evidencia.Idevidencia }, evidencia);
        }

        // DELETE: api/Evidencias/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteEvidencia([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var evidencia = await _context.Evidencia.FindAsync(id);
            if (evidencia == null)
            {
                return NotFound();
            }

            _context.Evidencia.Remove(evidencia);
            await _context.SaveChangesAsync();

            return Ok(evidencia);
        }

        private bool EvidenciaExists(int id)
        {
            return _context.Evidencia.Any(e => e.Idevidencia == id);
        }
    }
}