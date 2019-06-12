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
    public class ReportesController : ControllerBase
    {
        private readonly TransitoContext _context;

        public ReportesController(TransitoContext context)
        {
            _context = context;
        }

        // GET: api/Reportes
        [HttpGet]
        public IEnumerable<Reporte> GetReporte()
        {
            return _context.Reporte;
        }

        // GET: api/Reportes/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetReporte([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var reporte = await _context.Reporte.FindAsync(id);

            if (reporte == null)
            {
                return NotFound();
            }

            return Ok(reporte);
        }

        // PUT: api/Reportes/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutReporte([FromRoute] int id, [FromBody] Reporte reporte)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != reporte.Idreporte)
            {
                return BadRequest();
            }

            _context.Entry(reporte).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ReporteExists(id))
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

        // POST: api/Reportes
        [HttpPost]
        public async Task<IActionResult> PostReporte([FromBody] Reporte reporte)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.Reporte.Add(reporte);
            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateException)
            {
                if (ReporteExists(reporte.Idreporte))
                {
                    return new StatusCodeResult(StatusCodes.Status409Conflict);
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtAction("GetReporte", new { id = reporte.Idreporte }, reporte);
        }

        // DELETE: api/Reportes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteReporte([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var reporte = await _context.Reporte.FindAsync(id);
            if (reporte == null)
            {
                return NotFound();
            }

            _context.Reporte.Remove(reporte);
            await _context.SaveChangesAsync();

            return Ok(reporte);
        }

        private bool ReporteExists(int id)
        {
            return _context.Reporte.Any(e => e.Idreporte == id);
        }
    }
}