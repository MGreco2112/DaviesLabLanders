
## Create Aligned Data

### Create Aligned CTD Data

#### Pull all CTD Data by Head ID

```http
  GET ${api_host_url}/api/processed/ctd/data/headId/${id}
```

| Parameter      | Type     | Description                  |
| :------------- | :------- | :--------------------------- |
| `api_host_url` | `string` | **Required**. URL of API     |
| `id`           | `long`   | **Required**  ID of CTD Head |

#### Align CTD Data within an Array in this format

``` JSON
[
  {
    "rawID": ${data_point_ID},
    "oxygen_Sat_Percent": ${oxygen_percent_aligned_val},
    "pressure": ${pressure_aligned_val},
    "oxygen_ML_L": ${oxygen_ml_aligned_val},
    "turbidity_NTU": ${turbidity_aligned_val},
    "temperature_C": ${temperature_aligned_val},
    "chla_ug_mL": ${chla_aligned_val},
    "ctd_Flag": ${ctd_flag_aligned_val}
  },
  {
    {Next Aligned Data Point}
  }
]
```

#### Send array to this end point

```http
  PUT ${api_host_url}/api/processed/ctd/update/data/bulk_aligned
```

#### Call this route to see Aligned CTD Data

```http
  GET ${api_host_url}/api/processed/ctd/data/header/{id}/aligned/true
```

| Parameter      | Type     | Description                  |
| :------------- | :------- | :--------------------------- |
| `api_host_url` | `string` | **Required**. URL of API     |
| `id`           | `long`   | **Required**  ID of CTD Head |



### Create Aligned ADCP Data

#### Pull all ADCP Data by Head ID

```http
  GET ${api_host_url}/api/processed/adcp/data/headId/${id}
```

| Parameter      | Type     | Description                   |
| :------------- | :------- | :---------------------------- |
| `api_host_url` | `string` | **Required**. URL of API      |
| `id`           | `long`   | **Required**  ID of ADCO Head |

#### Align ADCP Data within an Array in this format

``` JSON
[
  {
    "rawID": ${data_point_ID},
    "horizontal_Current_Speed_cm_s": ${h_current_aligned_val},
    "current_Direction": ${cur_dir_aligned_val},
    "vertical_Current_Speed_cm_s": ${vert_cur_speed_aligned_val}
  },
  {
    {Next Aligned Data Point}
  }
]
```

#### Send array to this end point

```http
  PUT ${api_host_url}/api/processed/adcp/update/data/bulk_aligned
```

#### Call this route to see Aligned ADCP Data

```http
  GET ${api_host_url}/api/processed/adcp/data/header/{id}/aligned/true
```

| Parameter      | Type     | Description                  |
| :------------- | :------- | :--------------------------- |
| `api_host_url` | `string` | **Required**. URL of API     |
| `id`           | `long`   | **Required**  ID of ADCP Head |

