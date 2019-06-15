using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Reporte
    {
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
        public byte[] Fotos { get; set; }
        public int DictamenFolio { get; set; }
        public int Idevidencia { get; set; }
        public int Idcondutor { get; set; }

        public Aseguradora AseguradoraImplicadoNavigation { get; set; }
        public Dictamen DictamenFolioNavigation { get; set; }
        public Conductor IdcondutorNavigation { get; set; }
        public Evidencia IdevidenciaNavigation { get; set; }
    }
}
