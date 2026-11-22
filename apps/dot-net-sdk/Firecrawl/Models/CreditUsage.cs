using System.Text.Json.Serialization;

namespace Firecrawl.Models;

/// <summary>
/// Account credit usage information.
/// </summary>
public class CreditUsage
{
    [JsonPropertyName("remainingCredits")]
    public int? RemainingCredits { get; set; }

    [JsonPropertyName("planCredits")]
    public int? PlanCredits { get; set; }

    [JsonPropertyName("total_credits_used")]
    public int? TotalCreditsUsed { get; set; }

    [JsonPropertyName("billingPeriodStart")]
    public string? BillingPeriodStart { get; set; }

    [JsonPropertyName("billingPeriodEnd")]
    public string? BillingPeriodEnd { get; set; }
}
