using System;
using System.Collections.Generic;

namespace Transito.Models
{
    public partial class Evidencia
    {
        public Evidencia()
        {
            Reporte = new HashSet<Reporte>();
        }

        public int Idevidencia { get; set; }
        public byte[] FotoDerecha1 { get; set; }
        public byte[] FotoDerecha2 { get; set; }
        public byte[] FotoIzquierda1 { get; set; }
        public byte[] FotoIzquierda2 { get; set; }
        public byte[] FotoFrontal1 { get; set; }
        public byte[] FotoFrontal2 { get; set; }
        public byte[] FotoTrasera1 { get; set; }
        public byte[] FotoTrasera2 { get; set; }

        public ICollection<Reporte> Reporte { get; set; }
    }
}
