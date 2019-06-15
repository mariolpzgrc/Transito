using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Conductor
    {
        public Conductor()
        {
            ConductorBitacoraAcceso = new HashSet<ConductorBitacoraAcceso>();
            Reporte = new HashSet<Reporte>();
            Vehiculo = new HashSet<Vehiculo>();
        }

        public int Idconductor { get; set; }
        public string Nombre { get; set; }
        public string ApellidoPaterno { get; set; }
        public string ApellidoMaterno { get; set; }
        public DateTime FechaNacimiento { get; set; }
        public string NumeroLicencia { get; set; }
        public string Telefono { get; set; }
        public string Password { get; set; }

        public ICollection<ConductorBitacoraAcceso> ConductorBitacoraAcceso { get; set; }
        public ICollection<Reporte> Reporte { get; set; }
        public ICollection<Vehiculo> Vehiculo { get; set; }
    }
}
