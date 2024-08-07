@web
Feature: UI - Track Parcel

  Background: Open page
    Given I open Parcel Tracking page

  Scenario Outline: Check parcel latest status
    When I search for parcel using tracking number: <NUMBER>
    Then Parcel status is: <STATUS>

    Examples:
      | NUMBER                   | STATUS                   |
      | 520113014230722029585646 | Dostarczona              |
      | 520107010499997005638120 | Przekazana do doręczenia |
      | 523000016696115042036670 | Anulowano etykietę       |
      | 692402387409402026105505 | Dostarczona              |

  Scenario: Check parcel statuses
    When I search for parcel using tracking number: 692402387409402026105505
    Then Parcel statuses are:
      | Przyjęta w Centrum Logistycznym |
      | Wydana do doręczenia            |
      | Odebrana                        |
      | Anulowana                       |
