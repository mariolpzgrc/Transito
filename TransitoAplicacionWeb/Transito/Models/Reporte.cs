using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Reporte
    {
        public Reporte()
        {
            Dictamen = new HashSet<Dictamen>();
        }

        public int Idreporte { get; set; }
        public double Longitud { get; set; }
        public double Latitud { get; set; }
        public string Lugar { get; set; }
        public string NombreImplicado { get; set; }
        public int? AseguradoraImplicado { get; set; }
        public string NumeroPolizaImplicado { get; set; }
        public string MarcaImplicado { get; set; }
        public string ModeloImplicado { get; set; }
        public string ColorImplicado { get; set; }
        public string PlacaImplicado { get; set; }
        public DateTime FechaSuceso { get; set; }
        public int Idevidencia { get; set; }
        public int Idcondutor { get; set; }
        public bool Estatus { get; set; }

        public Aseguradora AseguradoraImplicadoNavigation { get; set; }
        public Conductor IdcondutorNavigation { get; set; }
        public Evidencia IdevidenciaNavigation { get; set; }
        public ICollection<Dictamen> Dictamen { get; set; }
    }
}
