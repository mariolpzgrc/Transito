using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class ConductorBitacoraAcceso
    {
        public int Id { get; set; }
        public int? Idconductor { get; set; }
        public DateTime? Fecha { get; set; }
        public string TokenAcceso { get; set; }
        public bool? Activa { get; set; }

        public Conductor IdconductorNavigation { get; set; }
    }
}
