using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Vehiculo
    {
        public string Placas { get; set; }
        public string Marca { get; set; }
        public string Modelo { get; set; }
        public int Anio { get; set; }
        public string Color { get; set; }
        public int Idaseguradora { get; set; }
        public string PolizaSeguro { get; set; }
        public int Idconductor { get; set; }

        public Aseguradora IdaseguradoraNavigation { get; set; }
        public Conductor IdconductorNavigation { get; set; }
    }
}
