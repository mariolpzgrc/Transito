using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Aseguradora
    {
        public Aseguradora()
        {
            Reporte = new HashSet<Reporte>();
            Vehiculo = new HashSet<Vehiculo>();
        }

        public int Idaseguradora { get; set; }
        public string Nombre { get; set; }

        public ICollection<Reporte> Reporte { get; set; }
        public ICollection<Vehiculo> Vehiculo { get; set; }
    }
}
