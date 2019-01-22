using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace FilmClub.CSharp.ASP.NETCore.Movies.Database
{
    [Table("movie")]
    public class Movie
    {
        [Column("id")]
        public int Id { get; set; }

        [Column("imagelink")]
        public string ImageLink { get; set; }

        [Column("name")]
        public string Name { get; set; }

        [Column("externalid")]
        public int IMDBId { get; set; }

        [Column("votes")]
        public int? votes { get; set; }
    }
}
