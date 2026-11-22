using System.Text.Json.Serialization;

namespace Firecrawl.Models;

/// <summary>
/// Current concurrency usage information.
/// </summary>
public class ConcurrencyCheck
{
    [JsonPropertyName("concurrency")]
    public int Current { get; set; }

    [JsonPropertyName("maxConcurrency")]
    public int MaxConcurrency { get; set; }
}
