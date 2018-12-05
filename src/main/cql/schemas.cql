
use billing;

create type if not exists bill_details (designation text, number int, price double, product text, unitPrice double);
create table if not exists tracking_billing (id uuid, campaignName text, codeClient text, details list<frozen <bill_details>>, refUser text, refMaileva text, refClient text, product text, PRIMARY KEY (id, codeClient)) with clustering order by (codeClient desc);